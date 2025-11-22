import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {

  const [characters, setCharacters] = useState([]);
  const [selectedCharacter, setSelectedCharacter] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchResidents = async (locationName = '') => {
    setError(null);
    setLoading(true);
    setSelectedCharacter(null);
    try {
      // Buscamos la localización
      const url = locationName = `https://rickandmortyapi.com/api/location/?name=${locationName}`;
      const response = await fetch(url);
      const data = await response.json();
      
      if(data.results && data.results.length > 0){
        // Devuelve la 1º localizacion encontrada
        const foundLocation = data.results[0];
        // Lista de residentes en la localizacion
        const residentsUrl = foundLocation.residents;
        // Lista vacia
        const residentsArray = [];

        for (let i = 0; i < residentsUrl.length; i++) {
          // Guardamos en la constante residente el residente de la lista
          const resident = residentsUrl[i];
          // Petición a API Characters para guardarlos
          const responseResident = await fetch(resident);
          const dataResident = await responseResident.json();
          residentsArray.push(dataResident);
        }
        // Guardamos el valor de los residentes en el array 
        setCharacters(residentsArray);
      }
    } catch (error) {
      setError("Fetch error")
    }finally{
      setLoading(false);
    }
  };

  const onSearch = (name) => {
    fetchResidents(name);
  };

  const onClick = (character) => {
    setSelectedCharacter(character);
  };

  const onClose = () => {
    setSelectedCharacter(null);
  };

  useEffect(() => {
    fetchResidents();
  }, []);

  
  return (
    <div className="App">
      <div className="search-container">
        <h1>Buscador de Residentes 🌍</h1>
        <SearchBar onSearch={onSearch}/>
      </div>

      <main>
        {loading && <p>Loading residents...</p>}
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
      </main>
    </div>
  );
}

export default App;
