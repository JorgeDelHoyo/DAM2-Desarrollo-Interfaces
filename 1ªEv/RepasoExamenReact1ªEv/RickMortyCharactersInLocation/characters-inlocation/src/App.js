import './App.css';
import SearchBar from './components/SearchBar';
import { useEffect, useState } from 'react';
import LocationList from './components/LocationList';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {

  const [locations, setLocations] = useState([]);
  const [locationSelected, setLocationSelected] = useState(null);
  const [characters, setCharacters] = useState([]);
  const [characterSelected, setCharacterSelected] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchLocations = async (name = '') => {
    setLoading(true);
    setError(null);
    setCharacterSelected(null);
    setCharacters([]);
    setLocationSelected(null);

    try {
      const url = name ? `https://rickandmortyapi.com/api/location/?name=${name}` : 'https://rickandmortyapi.com/api/location';
      const response = await fetch(url);
      const data = await response.json();

      if(response.ok){
        setLocations(data.results);
      } else {
        setError("Location error");
        setLocations([]);
      }
    } catch (err) {
      console.log(err);
      setError("Fetch error1");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchLocations();
  }, []);

  useEffect(() => {
    if(!locationSelected) return;

    async function fetchCharacters(){
      const residentsUrl = locationSelected.residents;
      const residentsArray = [];

      for (let i = 0; i < residentsUrl.length; i++) {
        const charUrl = residentsUrl[i];
        const respChar = await fetch(charUrl);
        const dataChar = await respChar.json();
        
        residentsArray.push(dataChar);
      }
      setCharacters(residentsArray);
    }

    fetchCharacters();
  }, [locationSelected]);

  const onSearch = (name) => {
    fetchLocations(name);
  };

  const onClick = (location) => {
    setLocationSelected(location)
  };

  const onClickCh = (character) => {
    setCharacterSelected(character);
  };

  const onClose = () => {
    setCharacterSelected(null);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Rick & Morty Searcher</h1>
        <SearchBar
          onSearch={onSearch}
        />
      </header>
      <main>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}

        {!loading && !locationSelected && (
          <LocationList
            locations={locations}
            onClick={onClick}
          />
        )}

        {locationSelected && (
          <CharacterList
            characters={characters}
            onClick={onClickCh}
          />
        )}

        {characterSelected && (
          <CharacterDetail
            characterSelected={characterSelected}
            onClose={onClose}
          />
        )}
      </main>
    </div>
  );
}

export default App;
