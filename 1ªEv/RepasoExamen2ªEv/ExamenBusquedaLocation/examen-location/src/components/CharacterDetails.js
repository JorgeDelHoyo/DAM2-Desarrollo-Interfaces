import { useEffect, useState } from 'react';
import './CharacterDetails.css';

function CharacterDetails({ location, closeDetails }) {
    const [locationData, setLocationData] = useState([]);

    const fetchLocationData = async () => {
        try {
            const response = await fetch(location);
            const data = await response.json();

            if (response.ok) {
                setLocationData(data || []);
            } else {
                setLocationData([]);
            }
        } catch (err) {
            setLocationData([]);
        }
    };

    useEffect(() => {
        fetchLocationData();
    }, [location]);

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button onClick={closeDetails} className='modal-close-button'>X</button>
                    <div className="modal-info">
                        <p className="info-label">Name: {locationData.name}</p>
                        <p className="info-label">Type: {locationData.type}</p>
                        <p className="info-label">Dimension: {locationData.dimension}</p>
                    </div>
            </div>
        </div>
    );
}

export default CharacterDetails;