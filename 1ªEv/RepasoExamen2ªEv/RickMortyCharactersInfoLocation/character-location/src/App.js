import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {

  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false)


  useEffect(() => {
    fetchCharacters();
  }, []);

  const fetchCharacters = async (name='') => {

    setError(null);
    setLoading(true);

    try{
      const url = name ? `https://rickandmortyapi.com/api/character/?name=${name}`:'https://rickandmortyapi.com/api/character' ;
      const response = await fetch(url)
      const data = await response.json()

      if(response.ok){
        setCharacters(data.results || [])
      }else{
        setError("Character error")
      }
    }catch(err){
      setError("Fetch error")
    }finally{
      setLoading(false)
    }
  }

  const handleSearch = (term) => {
    fetchCharacters(term)
    setSelectedCharacter(null)
  }

  const handleCharacterClick = (character) => {
    setSelectedCharacter(character);
  }

  const onClose = () => {
    setSelectedCharacter(null);
  }
  
  return (
    <div className="App">
      <header className="App-header">
        <SearchBar
        onSearch={handleSearch}
        />
      </header>
      <main>
        {!loading && (
          <CharacterList
            characters={characters}
            onCharacterClick={handleCharacterClick}
          />
        )}
        {selectedCharacter && (
          <CharacterDetail
            locationUrl={selectedCharacter.location.url}
            onClose={onClose}
          />
        )}
      </main>
    </div>
  );
}

export default App;
