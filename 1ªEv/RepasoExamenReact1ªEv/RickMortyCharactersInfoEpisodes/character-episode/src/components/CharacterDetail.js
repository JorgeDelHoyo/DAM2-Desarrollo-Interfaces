import './CharacterDetail.css';

function CharacterDetail({character, onClose}) {
    return(
        <div className="modal-overlay">
            <div className="modal-content">
                <button onClick={onClose}>X</button>
                <img src={character.image} />
                <h3>Name: {character.name}</h3>
                <p>Status: {character.status}</p>
                <p>Gender: {character.gender}</p>
                <p>Specie: {character.species}</p>
                <p>Location: {character.location.name}</p>
            </div>
        </div>
    );
}

export default CharacterDetail;