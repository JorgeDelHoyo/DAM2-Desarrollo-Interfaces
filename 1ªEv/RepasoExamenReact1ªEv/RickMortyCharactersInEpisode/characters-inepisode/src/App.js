import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import LimitSelector from './components/LimitSelector';
import EpisodeList from './components/EpisodeList';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {
  
  const [episodes, setEpisodes] = useState([]);
  const [selectedEpisode, setSelectedEpisode] = useState(null);
  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [limit, setLimit] = useState(50);

  const fetchEpisodes = async(name = '') => {
    setError(null);
    setLoading(true);
    setCharacters([]);
    setEpisodes([]);
    setSelectedCharacter(null);
    setSelectedEpisode(null);

    try {
      const url = name ? `https://rickandmortyapi.com/api/episode/?name=${name}`:'https://rickandmortyapi.com/api/episode';
      const response = await fetch(url);
      const data = await response.json();

      if(response.ok){
        setEpisodes(data.results);
      } else {
        setError("Episodes error");
        setEpisodes([]);
      }
    } catch (err) {
      console.log(err);
      setError("Episode fetch error")
    } finally {
      setLoading(false);
    }
  };

  const fetchCharacters = async() => {

    setError(null);
    setLoading(true);

    try {
      const characterUrls = selectedEpisode.characters;
      const tempArray = [];

      for (let i = 0; i < characterUrls.length; i++) {
        const urlCh = characterUrls[i];
        const respCh = await fetch(urlCh);
        const dataCh = await respCh.json();

        if(respCh.ok){
          tempArray.push(dataCh);
        } else {
          setError("Character error");
        }
      }

      setCharacters(tempArray);

    } catch (err) {
      console.log(err);
      setError("Characters Fetch error")
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEpisodes();
  }, []);

  useEffect(() => {
    if(!selectedEpisode) return;
    fetchCharacters();
  }, [selectedEpisode]);

  const onSearch = (name) => {
    fetchEpisodes(name);
  };

  const onClick = (episode) => {
    setSelectedEpisode(episode);
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
        <h1>Rick & Morty searcher</h1>
        <SearchBar onSearch={onSearch} />
      </header>
      <main>
        <LimitSelector
          limit={limit}
          onLimitChange={setLimit}
        />
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && !selectedEpisode && (
          <EpisodeList
            episodes={episodes.slice(0, limit)}
            onClick={onClick}
          />
        )}
        {selectedEpisode && (
          <CharacterList
            characters={characters.slice(0, limit)}
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
