import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import App from './App';
import fetchMock from 'jest-fetch-mock';

fetchMock.enableMocks();

beforeEach(() => {
  fetchMock.resetMocks();
});

describe('App Component', () => {
  test('renders without crashing', async () => {
    fetchMock.mockResponseOnce(JSON.stringify([]));

    render(<App />);
    expect(screen.getByText(/ToDo Liste/i)).toBeInTheDocument();
  });

  test('adds a new task', async () => {
    fetchMock.mockResponseOnce(JSON.stringify([]));
    fetchMock.mockResponseOnce(JSON.stringify({ taskdescription: 'New Task' }));

    render(<App />);

    const input = screen.getByRole('textbox');
    const button = screen.getByRole('button', { name: /Absenden/i });

    fireEvent.change(input, { target: { value: 'New Task' } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(screen.getByText('Task 1: New Task')).toBeInTheDocument();
    });
  });

  test('edits an existing task', async () => {
    fetchMock.mockResponseOnce(JSON.stringify([{ taskdescription: 'Task 1', done: false }]));
    fetchMock.mockResponseOnce(JSON.stringify({}));

    render(<App />);

    await waitFor(() => {
      expect(screen.getByText('Task 1: Task 1')).toBeInTheDocument();
    });

    const editButton = screen.getByRole('button', { name: /Edit/i });
    fireEvent.click(editButton);

    const input = screen.getByDisplayValue('Task 1');
    fireEvent.change(input, { target: { value: 'Updated Task 1' } });

    const submitButton = screen.getByRole('button', { name: /Submit/i });
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(screen.getByText('Task 1: Updated Task 1')).toBeInTheDocument();
    });
  });

  test('marks a task as done', async () => {
    fetchMock.mockResponseOnce(JSON.stringify([{ taskdescription: 'Task 1', done: false }]));
    fetchMock.mockResponseOnce(JSON.stringify({}));

    render(<App />);

    await waitFor(() => {
      expect(screen.getByText('Task 1: Task 1')).toBeInTheDocument();
    });

    const doneButton = screen.getByRole('button', { name: /Done/i });
    fireEvent.click(doneButton);

    await waitFor(() => {
      expect(screen.getByText('Task 1: Task 1')).toHaveClass('done');
    });
  });

  test('deletes a task', async () => {
    fetchMock.mockResponseOnce(JSON.stringify([{ taskdescription: 'Task 1', done: false }]));
    fetchMock.mockResponseOnce(JSON.stringify({}));

    render(<App />);

    await waitFor(() => {
      expect(screen.getByText('Task 1: Task 1')).toBeInTheDocument();
    });

    const deleteButton = screen.getByRole('button', { name: /Delete/i });
    fireEvent.click(deleteButton);

    await waitFor(() => {
      expect(screen.queryByText('Task 1: Task 1')).not.toBeInTheDocument();
    });
  });
});
