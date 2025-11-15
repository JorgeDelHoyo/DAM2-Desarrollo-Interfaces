import './App.css';
import { useState, useEffect } from 'react';
import CharacterModal from './components/CharacterModal';
import CharaterCard from './components/CharacterCard';

function App() {
  const [characters, setCharacters] = useState([]);
  const [nombre, setNombre] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [characterSelected, setCharacterSelected] = useState(null);

  useEffect(() => {
    fetchCharacters();
  }, []);

  const fetchCharacters = async (name='') => {
    setLoading(true);
    setError(null);
    setCharacterSelected(null);
    try{

      const url = name ? `https://rickandmortyapi.com/api/character/?name=${name}`: 'https://rickandmortyapi.com/api/character';

      const response = await fetch(url);
      const data = await response.json();

      if(response.ok){
        setCharacters(data.results || []);
      }else{
        setError("No characters found")
        setCharacters([]);
      }
    }catch(err){
      setError("Error fetching characters");
      setCharacters([]);
    }finally{
        setLoading(false);
      }
  };

  const openModal = (character) => {
    setCharacterSelected(character)
  };

  const closeModal = (character) => {
    setCharacterSelected(null);
  };



  return (
    <div className="App">
      <header className="App-header">
        <h1>🛸 Rick and Morty Character Explorer</h1>
        <input
          type='text'
          placeholder='Search by name'
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
        />
        <button onClick={() => fetchCharacters(nombre)}>Buscar</button>
      </header>
      <hr />
      {loading && <p>Loading characters... </p>}
      {error && <p className='error'>{error}</p>}

      <div className='character-grid'>
        {!loading && characters.map((character) => (
          <CharaterCard
            key={character.id}
            character={character}
            onCardClick={openModal}/>
        ))}
      </div>

      {characterSelected && (
        <CharacterModal
          character={characterSelected}
          onClose={closeModal}/>
      )}
    </div>
  );
}

export default App;
