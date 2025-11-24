import './LocationList.css';

function LocationList({locations, onClick}) {
    return(
        <div className="location-list">
            {locations.map((location) => (
                <h3
                    key={location.id} 
                    onClick={() => onClick(location)}
                    className="location-card"
                    >
                    
                    {location.name}
                </h3>
            ))}
        </div>
    );
}

export default LocationList;