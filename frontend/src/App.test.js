import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'; // for the toBeInTheDocument matcher
import App from './App'; // Adjust the import path as necessary

test('renders ToDo Liste', () => {
  render(<App />);
  const linkElement = screen.getByText("ToDo Liste");
  expect(linkElement).toBeInTheDocument();
});
