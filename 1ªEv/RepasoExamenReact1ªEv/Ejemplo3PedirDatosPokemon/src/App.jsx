import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [name, setName] = useState('');
  const [pokemon, setPokemon] = useState(null);
  const [error, setError] = useState(null);
  const[loading, setLoading] = useState(false);

  const pulsarBoton = async() => {
    setLoading(true);
    setError(null);
    setPokemon(null);

    try{
      const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);
      if(!response.ok){
        throw new Error(`No se encontró el pokemon ${name}`);
      }

      const data = await response.json();
      setPokemon(data);

    }catch(error){
      setError(error.message);
    }finally{
      setLoading(false);
    }
  }

  return (
    <div>
      <h2>Busca tu pokemon</h2>
      <input
        type="text"
        value={name}
        onChange={ (e) => setName(e.target.value)}
        placeholder='Introduce un nombre pokemon en minusculas(venasaur)'
      />
      <button onClick={pulsarBoton}>Buscar</button>
      <hr />
      {loading && <p>Cargando...</p>}
      {error && <p style={{color: 'red'}}>Error: {error}</p>}

      {
      pokemon && (
        <div>
          <h3>{pokemon.name}</h3>
          <img src={pokemon.sprites.other['official-artwork'].front_default} alt={pokemon.name} />
          <p>Tipo: {pokemon.types[0].type.name}</p>
        </div>
      )}
    </div>
  );
}

export default App
