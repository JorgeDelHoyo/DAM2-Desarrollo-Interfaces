import { useState } from "react";

function SearchBar({onSearch}) {
    const [searchTerm, setSearchTerm] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onSearch(searchTerm);
    }

    return(
        <form onSubmit={handleSubmit}>
            <input 
            type="text"
            value={searchTerm}
            placeholder="Busca un personaje"
            onChange={(e) => setSearchTerm(e.target.value)}/>
        <button type="submit">Buscar</button>
        </form>
    );
}

export default  SearchBar;