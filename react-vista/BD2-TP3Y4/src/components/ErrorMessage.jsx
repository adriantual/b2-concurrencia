// ErrorMessage.jsx

import React from 'react';

const ErrorMessage = ({ mensaje }) => {
  return (
    mensaje ? <div style={{ color: 'red' }}>{mensaje}</div> : null
  );
};

export default ErrorMessage;
