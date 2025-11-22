import { useEffect, useState } from 'react';
import './App.css';
import CharacterDesplegado from './componentes/CharacterDesplegado';
import SearchBar from './componentes/SearchBar';
import CharacterList from './componentes/CharacterList';

function App() {
  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchCharacters();
  }, []);

  const fetchCharacters = async (name = '') => {
    try {
      const url = name
        ? `https://rickandmortyapi.com/api/character/?name=${name}`
        : 'https://rickandmortyapi.com/api/character';
      const response = await fetch(url);
      const data = await response.json();

      if(response.ok){
        setCharacters(data.results);
      }else{
        setError("Character error")
        setCharacters([]);
      }
    } catch (err) {
      setError("Fetch error")
    }finally{
      setLoading(false);
    }
  };

  const handleOnSearch = (term) => {
    setSearchTerm(term);
    fetchCharacters(term);
    setSelectedCharacter(null);
  };

  const openDetails = (character) => {
    setSelectedCharacter(character);
  }

  const closeDetails = () => {
    setSelectedCharacter(null);
  }

  return(
    <div className='App'>
      <header className='App-header'>
        <h1>Rick & morty Searcher</h1>
        <SearchBar onSearch={handleOnSearch} />
      </header>
      <div className='main-content'>
        {loading && <p className='status-message loading'>Loading...</p>}
        {error && <p className='status-message'>{error}</p>}
        {!loading &&  (
          <CharacterList
          characters={characters}
          openDetails={openDetails}
          />
        )}
        {selectedCharacter && (
          <CharacterDesplegado
          selectedCharacter={selectedCharacter}
          closeDetails={closeDetails}
          />
        )}
      </div>
    </div>
  );

}

export default App;
