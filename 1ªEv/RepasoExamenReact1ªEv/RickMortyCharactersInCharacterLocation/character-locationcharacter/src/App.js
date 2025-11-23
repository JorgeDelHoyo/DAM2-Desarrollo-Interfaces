import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import SearchBar from './components/SearchBar';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {
  const [characters, setCharacters] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  // Info Personajes
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [isLocationView, setIsLocationView] = useState(false); // Flag


  const fetchCharacters = async (name='') => {
    setLoading(true);
    setError(null);
    setCharacters([]);
    setSelectedCharacter(null);
    setIsLocationView(false); // No estamos en location
    try {
      const url = name ? `https://rickandmortyapi.com/api/character/?name=${name}` : 'https://rickandmortyapi.com/api/character';
      const response = await fetch(url);
      const data = await response.json();

      if(data.results){
        setCharacters(data.results);
      }else{
        setError("Character error")
      }
    } catch (err) {
      setError("Fetch error")
    } finally {
      setLoading(false);
    }
  };

  const fetchCharactersLocation = async (character) => {
    const locationUrl = character.location.url;

    if(!locationUrl) {
      setError("Unknown ubication");
      return;
    }

    setLoading(true);
    setError(null);
    setCharacters([]);
    try {
      const responseLocation = await fetch(locationUrl);
      const dataLocation = await responseLocation.json();

      const residentsUrls = dataLocation.residents;
      const arrayResidents = [];

      if(residentsUrls.length > 0) {
        for (let i = 0; i < residentsUrls.length; i++) {
          const url = residentsUrls[i];
          const resp = await fetch(url);
          const dta = await resp.json();

          arrayResidents.push(dta);
        }
        setCharacters(arrayResidents);
        setIsLocationView(true); // Estamos en location
      } else {
        setError("Desert location")
      }
    } catch (err) {
      setError("Fetch error2")
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCharacters();
  }, []);

  const onClick = (character) => {
    if(isLocationView){
      setSelectedCharacter(character);
    } else{
      fetchCharactersLocation(character);
    }
  }

  const onClose = () => {
    setSelectedCharacter(null);
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>Buscador de Residentes 🌍</h1>
        <SearchBar onSearch={fetchCharacters}/>
      </header>
      <main>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}

        {!loading && (
          <CharacterList 
            characters={characters}
            onClick={onClick}/>
        )}

        {selectedCharacter && (
          <CharacterDetail
            character={selectedCharacter}
            onClose={onClose}
          />
        )}
      </main>
    </div>
  );
}

export default App;
