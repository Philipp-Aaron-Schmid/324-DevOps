import { render, screen } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});

global.fetch = jest.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve([
      { taskdescription: 'Task 1' },
      { taskdescription: 'Task 2' }
    ])
  })
);

test('gets ToDo Liste', async () => {
  render(<App />);

  const task1 = await screen.findByText('Task 1');
  const task2 = await screen.findByText('Task 2');

  expect(task1).toBeInTheDocument();
  expect(task2).toBeInTheDocument();
});