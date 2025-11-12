import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [id, setId] = useState('1');

  const [character, setCharacter] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  // 4. Esta función se llamará cuando el usuario pulse el botón
  const pulsarBoton = async () => {
    setLoading(true); // Empezamos a cargar
    setError(null); // Limpiamos errores anteriores
    setCharacter(null); // Limpiamos el personaje anterior

    try{
      // 5. ¡LA CLAVE! La URL del fetch ahora usa el 'id' del estado
      const response = await fetch(`https://rickandmortyapi.com/api/character/${id}`);

      // Si el ID no existe (ej: 99999), la API da un error 404
      if(!response.ok){
        throw new Error(`No se encontró el personaje con ID: ${ID}`);
      }

      const data = await response.json();

      // 6. Guardamos el objeto COMPLETO del personaje en el estado
      setCharacter(data);

    }catch(error){
      // Guardamos el error
      setError(error.message);
    }finally{
      // Haya éxito o error, dejamos de cargar
      setLoading(false);
    }
  };

  return (
      <div>
        <h2>Busca tu personaje de Rick & Morty</h2>
        <input
          type="number"
          value={id}
          onChange={ (e) => setId(e.target.value) }
          placeholder='Introduce un ID (ej: 1, 2, 3...)'
          min="1"
          max="827"
        />
        <button onClick={pulsarBoton}>Buscar</button>

        <hr />

        {character && (
        <div>
          <h3>{character.name}</h3>
          <p>Espeie: {character.species}</p>
          <p>Estado: {character.status}</p>
          <img src={character.image} alt={character.name} />
        </div>
        )}

      </div>
  );
}

export default App;