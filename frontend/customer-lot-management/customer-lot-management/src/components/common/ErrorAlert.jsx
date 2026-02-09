import React from 'react';
import './ErrorAlert.css';

const ErrorAlert = ({ message, onClose }) => {
  return (
    <div className="error-alert">
      <span className="error-message">{message}</span>
      {onClose && (
        <button className="error-close" onClick={onClose}>×</button>
      )}
    </div>
  );
};

export default ErrorAlert;