import logo from './logo.svg';
import './App.css';
import React from 'react';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      todos: typeof props.todos === 'undefined' ? [] : props.todos,
      taskdescription: "",
      editingTask: null, // Track which task is being edited
    };
  }

  handleChange = event => {
    this.setState({ taskdescription: event.target.value });
  }

  handleEditChange = (event, index) => {
    const newTodos = [...this.state.todos];
    newTodos[index].taskdescription = event.target.value;
    this.setState({ todos: newTodos });
  }

  handleSubmit = event => {
    event.preventDefault();
    fetch("http://localhost:8080/tasks", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ taskdescription: this.state.taskdescription })
    })
    .then(response => response.json())
    .then(response => {
      this.setState({
        todos: [...this.state.todos, {taskdescription: this.state.taskdescription, done: false}],
        taskdescription: ""
      });
    })
    .catch(error => console.log(error))
  }

  componentDidMount() {
    fetch("http://localhost:8080")
      .then(response => response.json())
      .then(data => {
        this.setState({todos: data});
      })
      .catch(error => console.log(error))
  }

  handleClick = taskdescription => {
    fetch(`http://localhost:8080/delete`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ taskdescription: taskdescription })
    })
    .then(response => {
      window.location.href = "/";
    })
    .catch(error => console.log(error))
  }

  toggleEdit = (index) => {
    this.setState((prevState) => ({
      editingTask: prevState.editingTask === index ? null : index
    }));
  }

  markAsDone = (index) => {
    const newTodos = [...this.state.todos];
    newTodos[index].done = !newTodos[index].done;
    this.setState({
      todos: newTodos.sort((a, b) => a.done - b.done)
    });
  }

  renderTasks(todos) {
    return (
      <ul>
        {todos.map((todo, index) => (
          <li key={todo.taskdescription} className={`TaskItem ${todo.done ? 'done' : ''}`}>
            {this.state.editingTask === index ? (
              <input
                type="text"
                value={todo.taskdescription}
                onChange={(e) => this.handleEditChange(e, index)}
              />
            ) : (
              <span>{"Task " + (index + 1) + ": " + todo.taskdescription}</span>
            )}
            <div className='buttons'>
              {this.state.editingTask === index ? (
                <button onClick={() => this.toggleEdit(index)}>Submit</button>
              ) : (
                <button onClick={() => this.toggleEdit(index)}>Edit</button>
              )}
              <button onClick={() => this.markAsDone(index)}>Done</button>
              <button onClick={() => this.handleClick(todo.taskdescription)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    );
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <div>
            <img onContextMenu={(e) => e.preventDefault()} src={logo} className="App-logo" alt="logo" />
          </div>
          <div className='Task'>
            <h1>
              ToDo Liste
            </h1>
            <form onSubmit={this.handleSubmit}>
              <input
                type="text"
                value={this.state.taskdescription}
                onChange={this.handleChange}
              />
              <button type="submit">Absenden</button>
            </form>
            <div className='Tasks'>
              {this.renderTasks(this.state.todos)}
            </div>
          </div>
        </header>
      </div>
    );
  }
}

export default App;
