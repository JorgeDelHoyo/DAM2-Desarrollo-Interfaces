import './App.css';
import { useEffect, useState } from 'react';
import SearchBar from './components/SearchBar';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {

  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [isEpisodeCharacter, setIsEpisodeCharacter] = useState(false); // Flag
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchCharacters = async (name='') => {
    setLoading(true);
    setError(null);
    setCharacters([]);
    setSelectedCharacter(null);
    setIsEpisodeCharacter(false); // Flag

    try {
      const url = name ? `https://rickandmortyapi.com/api/character/?name=${name}` : 'https://rickandmortyapi.com/api/character';
      const response = await fetch(url);
      const data = await response.json();

      if(data.results){
        setCharacters(data.results);
      } else {
        setError("Character error");
      }
    } catch (err) {
      console.log(err);
      setError("Fetch error 1")
    } finally {
      setLoading(false);
    }
  };

  const fetchEpisodeCharacters = async (character) => {
    if(!character.episode || character.episode.length === 0) return;

    const characterEpisodeUrl = character.episode[0]; // Primer episodio
    setError(null);
    setLoading(true);

    try {
      const respEpisode = await fetch(characterEpisodeUrl);
      const dataEpisode = await respEpisode.json();
      const charactersEpisode = dataEpisode.characters;
      const arrayCharacters = [];
      
      for (let i = 0; i < charactersEpisode.length; i++) {
        const urlEp = charactersEpisode[i];
        const respEp = await fetch(urlEp);
        const dataEp = await respEp.json();

        arrayCharacters.push(dataEp);
      }
      setCharacters(arrayCharacters);
      setIsEpisodeCharacter(true); // Flag
    } catch (err2) {
      console.log(err2);
      setError("Character error 2");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCharacters();
  }, []);

  const onClick = (character) => {
    if(isEpisodeCharacter){
      setSelectedCharacter(character)
    } else {
      fetchEpisodeCharacters(character);
    }
  };

  const onClose = () => {
    setSelectedCharacter(null);
  };


  return (
    <div className="App">
      <header className="App-header">
        <SearchBar onSearch={fetchCharacters}/>
      </header>

      <main>
        {loading && <p>Loading...</p>}
        {error && <p className='error-msg'>{error}</p>}
        
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
      </main>
    </div>
  );
}

export default App;
