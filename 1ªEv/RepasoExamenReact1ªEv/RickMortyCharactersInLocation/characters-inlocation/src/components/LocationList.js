function LocationList({locations, onClick}) {
    return(
        <div className="list-container">
            {locations.map((location) => (
                <h3 key={location.id}
                    onClick={() => onClick(location)}
                    className="list-item"
                >{location.name}</h3>
            ))}
        </div>
    );
}

export default LocationList;