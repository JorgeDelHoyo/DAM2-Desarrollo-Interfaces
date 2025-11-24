import { use, useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
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

    const fetchEpisodes = async(name= '') => {
      setLoading(true);
      setError(null);
      setEpisodes([]);
      setSelectedCharacter(null);
      setSelectedEpisode(null);
      setCharacters([]);
      try {
        const url = name ? `https://rickandmortyapi.com/api/episode/?name=${name}` : 'https://rickandmortyapi.com/api/episode' ;
        const response = await fetch(url);
        const data = await response.json();

        if(response.ok){
          setEpisodes(data.results);
        } else {
          setError("Episode error");
        }
      } catch (err) {
        setError("FetchError");
      } finally {
        setLoading(false);
      }
    };

    useEffect(() => {
      fetchEpisodes();
    }, []);

    useEffect(() => {
      if(!selectedEpisode) return;

      async function fetchCharacters(){
        const urlCharactersEpisode = selectedEpisode.characters;
        const charactersArray = [];

        for (let i = 0; i < urlCharactersEpisode.length; i++) {
          const urlCh = urlCharactersEpisode[i];
          const respCh = await fetch(urlCh);
          const dataCh = await respCh.json();
          
          charactersArray.push(dataCh);
        }
        setCharacters(charactersArray);
      } 

      fetchCharacters();
    }, [selectedEpisode]);

    const onSearch = (name) => {
      fetchEpisodes(name);
    };

    const onClick = (selectedEpisode) => {
      setSelectedEpisode(selectedEpisode);
    };

    const onClickCh = (character) => {
      setSelectedCharacter(character);
    }

    const onClose = () => {
      setSelectedCharacter(null);
    };

  return (
    <div className="App">
      <header className="App-header">
        <SearchBar onSearch={onSearch} />
      </header>
      <main>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}

        {!loading && !selectedEpisode && (
          <EpisodeList
            episodes={episodes}
            onClick={onClick}
          />
        )}
        {selectedEpisode && (
          <CharacterList
            characters={characters}
            onClick={onClickCh}
          />
        )}

        {selectedCharacter && (
          <CharacterDetail
            selectedCharacter={selectedCharacter}
            onClose={onClose}
          />
        )}
      </main>
    </div>
  );
}

export default App;
