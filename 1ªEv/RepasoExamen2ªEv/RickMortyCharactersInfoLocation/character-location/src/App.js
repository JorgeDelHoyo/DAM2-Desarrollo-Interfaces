import { useEffect, useState } from 'react';
import './App.css';
import SearchBar from './components/SearchBar';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {

  const [selectedLocationURL, setSelectedLocationURL] = useState(null);
  const [error, setError] = useState(null);;
  const [loading, setLoading] = useState(false);

  const handleSearch = async (locationName) => {
    setSelectedLocationURL(null);
    setLoading(true);
    setError(null);

    try {
      const response = await fetch(`https://rickandmortyapi.com/api/location/?name=${locationName}`);
      const data = await response.json();

      if(data.results && data.results.length > 0){
        setSelectedLocationURL(data.results[0].url);
      }else{
        setLoading("Character error")
      }
    } catch (err) {
      setError("Fetch error")
    }finally{
      setLoading(false);
    }
  };

  const onClose = () => {
    setSelectedLocationURL(null);
  }
  
  return (
    <div className="App">
      <div className="search-container">
        <h1>Buscador de Residentes 🌍</h1>
        <SearchBar onSearch={handleSearch} />
      </div>

      <main>
        {error && <div className="error-msg">{error}</div>}

        {/* Si tenemos una URL, mostramos tu componente superpuesto */}
        {selectedLocationURL && (
          <div className="modal-overlay">
            <div className="modal-content">
              {/* Tu componente inteligente hace todo el trabajo aquí dentro */}
              <CharacterDetail 
                locationUrl={selectedLocationURL} 
                onClose={onClose} 
              />
            </div>
          </div>
        )}
      </main>
    </div>
  );
}

export default App;
