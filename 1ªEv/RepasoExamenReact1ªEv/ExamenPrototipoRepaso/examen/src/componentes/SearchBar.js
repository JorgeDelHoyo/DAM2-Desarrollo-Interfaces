import { useState } from "react";
import './SearchBar.css';

function SearchBar({ onSearch }) {
    const [input, setInput] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onSearch(input);
    };

    return (
        <form className="search-form" onSubmit={handleSubmit}>
            <input
                className="search-input"
                type="text"
                value={input}
                onChange={(e) => setInput(e.target.value)}
                placeholder="Search here..."
            />
            <button className="search-button" type="submit">Search</button>
        </form>
    );
}

export default SearchBar;