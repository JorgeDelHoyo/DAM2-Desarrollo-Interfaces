import { useFetch } from './useFetch';
import './App.css'

function App() {
  const { data, loading, error, handleCancelRequest } = useFetch("https://jsonplaceholder.typicode.com/users");
  //El JSX que se muestra en pantalla
  return (
    <div className='App'>
      <h2>Fetch Like a PRO</h2>
      <button onClick={handleCancelRequest}>Cancel Request</button>
      <div className='card'>
        <ul>
          {error && <li>Error: {error}</li>}
          {loading && <li>Loading...</li>}
          {data?.map((user) => (
            <li key={user.id}>{user.name}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;