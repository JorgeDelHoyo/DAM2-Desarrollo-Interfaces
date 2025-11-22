import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {

  const[imageUrl, setImageUrl] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const pulsarBoton = async() => {

    setLoading(true);
    setError(null);
    setImageUrl(null);

    try{
      const response = await fetch(`https://dog.ceo/api/breeds/image/random`);
      if(!response.ok){
        throw new Error(`No se pudo cargar el perro`);
      }

      const data = await response.json();
      setImageUrl(data.message);
    }catch(error){
      setError(error.message);
    }finally{
      setLoading(false);
    }
  }

  return (
    <div>
      {loading && <p>Cargando...</p>}
      <button onClick={pulsarBoton}>Perro Nuevo</button>
      <hr />
      <img src={imageUrl} alt="Perro" />
    </div>
  );
}

export default App;