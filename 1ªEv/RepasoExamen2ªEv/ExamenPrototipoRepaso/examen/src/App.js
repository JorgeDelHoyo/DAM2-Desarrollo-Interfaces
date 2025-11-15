import { useEffect, useState } from 'react';
import CharacterCard from './componentes/CharacterCard';
import CharacterDesplegado from './componentes/CharacterDesplegado';
import './App.css';

function App() {
  const [nombre, setNombre] = useState('');
  const [characters, setCharacters] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [selectedCharacter, setSelectedCharacter] = useState(null);

  useEffect(() => {
    fetchCharacters();
  }, []);

  const fetchCharacters = async (name='') => {
    setLoading(true);
    setError(null);
    setSelectedCharacter(null);

    try{
      const url = name ? `https://rickandmortyapi.com/api/character/?name=${name}` : 'https://rickandmortyapi.com/api/character';

        const response = await fetch(url);
        const data = await response.json();

        if(response.ok){
          setCharacters(data.results || []);
        }else{
          setError("Character error")
          setCharacters([]);
        }
    }catch(err){
      setError("Fetch error")
      setCharacters([]);
    }finally{
      setLoading(false);
    }
  }

  const mostrarDetalles = (character) => {
    setSelectedCharacter(character);
  };

  const cerrarDetalles = (character) => {
    setSelectedCharacter(null);
  }

  return(
    <div>
      <header className='App-header'>
        <h1>Buscador Rick y Morty</h1>
        <input
          type='text'
          placeholder='Busca aqui tu personaje'
          onChange={(e) => setNombre(e.target.value)}
        />
        <button onClick={ () => fetchCharacters(nombre)}>Buscar</button>
      </header>
      {loading && <p>Cargando personajes...</p>}
      {error && <p className='error'>{error}</p>}

      <div className='character-grid'>
        {!loading && characters.map((character) => (
          //Carta personaje
          <CharacterCard
            key={character.id}
            personaje={character}
            mostrarDetalles={mostrarDetalles}
          />
        ))}
      </div>
      {selectedCharacter && (
        //Carta xpandida
        <CharacterDesplegado
          personajeSeleccionado={selectedCharacter}
          cerrarDetalles={cerrarDetalles}
        />
      )}
    </div>
  );

}

export default App;
