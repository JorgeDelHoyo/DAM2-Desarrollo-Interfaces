import { useState } from "react";
import './SearchBar.css'

function SearchBar({onSearch}) {
    const [searchTerm, setSearchTerm] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onSearch(searchTerm);
    }

    return(
        <form onSubmit={handleSubmit} className="search-bar">
            <input
            type="text"
            value={searchTerm}
            placeholder="Type here ..."
            onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button type="submit">Search</button>
        </form>
    );
}

export default SearchBar;