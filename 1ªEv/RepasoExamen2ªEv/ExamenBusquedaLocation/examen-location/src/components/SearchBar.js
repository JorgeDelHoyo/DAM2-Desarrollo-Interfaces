import { useState } from "react";
import './SearchBar.css';

function SearchBar ({onSearch}) {
    const[input, setInput] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onSearch(input);
    };

    return(
        <form onSubmit={handleSubmit} className="search-form">
            <input
                className="search-input"
                type="text"
                value={input}
                onChange={(e) => setInput(e.target.value)}
                placeholder="Search here..."
            />
            <button type="submit" className="search-button">Search</button>
        </form>
    );
}

export default SearchBar;