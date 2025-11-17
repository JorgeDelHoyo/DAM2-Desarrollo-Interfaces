import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import CharacterList from './components/CharacterList';
import CharacterDetails from './components/CharacterDetails';

function App() {

  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchCharacters();
  }, []);

  const fetchCharacters = async (name='') => {
    setError(null);
    setLoading(true);
    try {
      const url = name
        ? `https://rickandmortyapi.com/api/character/?name=${name}`
        : 'https://rickandmortyapi.com/api/character';
      const response = await fetch(url);
      const data = await response.json();

      if(response.ok){
        setCharacters(data.results || []);
      }else{
        setError("Error character");
        setCharacters([]);
      }
    } catch (err) {
      setError("Error fetch");
      setCharacters([]);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = (term) => {
    setSearchTerm(term);
    fetchCharacters(term);
    setSelectedCharacter(null);
  }

  const openDetails = (character) => {
    setSelectedCharacter(character);
  }

  const closeDetails = () => {
    setSelectedCharacter(null);
  }


  return (
    <div className="App">
      <header className="App-header">
        <h1>Rick & Morty Search</h1>
        <SearchBar onSearch={handleSubmit}/>
      </header>
      <div className='main-content'>
        {loading && <p className='status-loading'>Loading...</p>}
        {error && <p className='status-error'>{error}</p>}
        {!loading && (
          <CharacterList
            characters={characters}
            openDetails={openDetails}
          />
        )}
        {selectedCharacter && (
          <CharacterDetails
            location={selectedCharacter.location.url}
            closeDetails={closeDetails}
          />
        )}
      </div>
    </div>
  );
}

export default App;
