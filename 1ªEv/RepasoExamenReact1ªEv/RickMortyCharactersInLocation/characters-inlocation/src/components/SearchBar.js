import { useState } from "react";

function SearchBar({onSearch}) {
    const [searchTerm, setSearchTerm] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onSearch(searchTerm);
    };

    return(
        <form onSubmit={handleSubmit} className="search-form">
            <input
                type="text"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                placeholder="Search here..."
                className="search-input"
            />
            <button type="submit" className="search-button">Search</button>
        </form>
    );
}

export default SearchBar;