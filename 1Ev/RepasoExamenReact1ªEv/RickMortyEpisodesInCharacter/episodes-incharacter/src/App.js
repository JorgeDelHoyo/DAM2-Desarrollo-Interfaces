import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import LimitSelector from './components/LimitSelector';
import CharacterList from './components/CharacterList';
import EpisodeList from './components/EpisodeList';

function App() {

  const [episodes, setEpisodes] = useState([]);
  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [limit, setLimit] = useState(999);

  const fetchCharacters = async(name = '') => {
    setError(null);
    setLoading(true);
    setEpisodes([]);
    setCharacters([]);
    setSelectedCharacter(null);

    try {
      const url = name ? `https://rickandmortyapi.com/api/character/?name=${name}`:'https://rickandmortyapi.com/api/character';
      const response = await fetch(url);
      const data = await response.json();

      if(response.ok){
        setCharacters(data.results);
      } else {
        setError("Character Error");
        setCharacters([]);
      }
    } catch (err) {
      console.log(err);
      setError("Error Fetch Character");
    } finally {
      setLoading(false);
    }
  };

  const fetchEpisodes = async() => {
    setError(null);
    setLoading(true);
    
    try {
      const episodesUrl = selectedCharacter.episode;
      const arrayTemp = [];

      for (let i = 0; i < episodesUrl.length; i++) {
        const urlEp = episodesUrl[i];
        const respEp = await fetch(urlEp);
        const dataEp = await respEp.json();

        if(respEp.ok) {
          arrayTemp.push(dataEp);
        } else {
          setError("Episode error");
        }
      }

      setEpisodes(arrayTemp);

    } catch (err) {
      console.log(err);
      setError("Episodes fetch error");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCharacters();
  }, []);

  useEffect(() => {
    if(!selectedCharacter) return;
    fetchEpisodes();
  }, [selectedCharacter]);

  const onSearch = (name) => {
    fetchCharacters(name);
  };

  const onClick = (character) => {
    setSelectedCharacter(character);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Rick & Morty Searcher</h1>
        <SearchBar  onSearch={onSearch}/>
      </header>
      <main>
        <LimitSelector
          limit={limit}
          onChangeLimit={setLimit}
        />
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && !selectedCharacter && (
          <CharacterList
            characters={characters.slice(0,limit)}
            onClick={onClick}
          />
        )}
        {selectedCharacter && (
          <EpisodeList
            episodes={episodes.slice(0, limit)}
          />
        )}
      </main>
    </div>
  );
}

export default App;
