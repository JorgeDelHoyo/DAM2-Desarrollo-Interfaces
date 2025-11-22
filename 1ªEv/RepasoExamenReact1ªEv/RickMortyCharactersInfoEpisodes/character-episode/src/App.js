import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [characters, setCharacters] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);


  const fetchEpisodes = async (episodeName = '') => {
    setLoading(true);
    setError(null);
    setSelectedCharacter(null);
    try {
      const url = episodeName ? `https://rickandmortyapi.com/api/episode/?name=${episodeName}` : 'https://rickandmortyapi.com/api/episode';  
      const response = await fetch(url);
      const data = await response.json();

      if(data.results && data.results.length > 0){
        const foundEpisode = data.results[0];
        const charactersEpisodeUrl = foundEpisode.characters;
        const arrayCharactersEpisode = [];

        for (let i = 0; i < charactersEpisodeUrl.length; i++) {
          const charactersEpisode = charactersEpisodeUrl[i];
          const responseCharactersEpisode = await fetch(charactersEpisode);
          const dataCharactersEpisode = await responseCharactersEpisode.json();

          arrayCharactersEpisode.push(dataCharactersEpisode);
        }
        setCharacters(arrayCharactersEpisode);
      }
    } catch (err) {
      setError("Fetch error")
      console.log(err)
    } finally {
      setLoading(false);
    }
  };

  const onSearch = (nameEpisode) => {
    fetchEpisodes(nameEpisode)
  }

  const onClick = (character) => {
    setSelectedCharacter(character);
  }

  const onClose = () => {
    setSelectedCharacter(null);
  }


  useEffect(() => {
    fetchEpisodes();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>Buscador de Residentes 🌍</h1>
        <SearchBar onSearch={onSearch}/>
      </header>
      <div>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && (
          <CharacterList
            characters={characters}
            onClick={onClick}
          />
        )}
        {selectedCharacter && (
          <CharacterDetail
            character={selectedCharacter}
            onClose={onClose}
          />
        )}
      </div>
    </div>
  );
}

export default App;
