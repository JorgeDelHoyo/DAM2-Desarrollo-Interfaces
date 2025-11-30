import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import LocationList from './components/LocationList';
import LimitSelector from './components/LimitSelector';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {

  const [locations, setLocations] = useState([]);
  const [selectedLocations, setSelectedLocations] = useState(null);
  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [limit, setLimit] = useState(999); // Todos

  const fetchLocations = async(name = '') => {
    setError(null);
    setLoading(true);
    setCharacters([]);
    setLocations([]);
    setSelectedCharacter(null);
    setSelectedLocations(null);

    try {
      const url = name ? `https://rickandmortyapi.com/api/location/?name=${name}`:'https://rickandmortyapi.com/api/location';
      const response = await fetch(url);
      const data = await response.json();

      if(response.ok){
        setLocations(data.results);
      } else {
        setLocations([]);
        setError("Location error");
      }
    } catch (err) {
      console.log(err);
      setError("Locations fetch error");
    } finally {
      setLoading(false);
    }
  };

  const fetchCharacters = async() => {
    setError(null);
    setLoading(true);
    
    try {
      const residentsUrl = selectedLocations.residents;
      const arrayTemp = [];

      for (let i = 0; i < residentsUrl.length; i++) {
        const urlCh = residentsUrl[i];
        const respCh = await fetch(urlCh);
        const dataCh = await respCh.json();

        if(respCh.ok){
          arrayTemp.push(dataCh);
        } else {
          setError("Character Url error");
        }
      }

      setCharacters(arrayTemp);

    } catch (err) {
      console.log(err);
      setError("Character fetch error");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchLocations();
  }, []);

  useEffect(() => {
    if(!selectedLocations) return;
    fetchCharacters();
  }, [selectedLocations]);

  const onSearch = (name) => {
    fetchLocations(name);
  };

  const onClick = (location) => {
    setSelectedLocations(location);
  };

  const onClickCh = (character) => {
    setSelectedCharacter(character);
  };

  const onClose = () => {
    setSelectedCharacter(null);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Rick & Morty Searcher</h1>
        <SearchBar onSearch={onSearch} />
      </header>
      <main>
        <LimitSelector 
          limit={limit}
          onChangeLimit={setLimit}
        />
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && !selectedLocations && (
          <LocationList
            locations={locations.slice(0, limit)}
            onClick={onClick}
          />
        )}
        {selectedLocations && (
          <CharacterList
            characters={characters.slice(0,limit)}
            onClick={onClickCh}
          />
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
