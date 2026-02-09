import React, { useState } from 'react';
import './SearchBar.css';

const SearchBar = ({ placeholder = 'Поиск...', value, onChange, delay = 300 }) => {
  const [searchValue, setSearchValue] = useState(value || '');

  const handleChange = (e) => {
    const newValue = e.target.value;
    setSearchValue(newValue);
    
    if (delay) {
      clearTimeout(window.searchTimeout);
      window.searchTimeout = setTimeout(() => {
        onChange(newValue);
      }, delay);
    } else {
      onChange(newValue);
    }
  };

  const handleClear = () => {
    setSearchValue('');
    onChange('');
  };

  return (
    <div className="search-bar">
      <input
        type="text"
        placeholder={placeholder}
        value={searchValue}
        onChange={handleChange}
        className="search-input"
      />
      {searchValue && (
        <button className="search-clear" onClick={handleClear}>
          ×
        </button>
      )}
    </div>
  );
};

export default SearchBar;