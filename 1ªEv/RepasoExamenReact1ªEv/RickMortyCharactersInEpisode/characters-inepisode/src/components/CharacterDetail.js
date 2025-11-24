import './CharacterDetail.css';

function CharacterDetail({selectedCharacter, onClose}) {
    return(
        <div className="modal-overlay">
            <div className="modal-content">
                <button onClick={onClose}>X</button>
                <img src={selectedCharacter.image} />
                <h3>Name: {selectedCharacter.name}</h3>
                <p>Status: {selectedCharacter.status}</p>
                <p>Gender: {selectedCharacter.gender}</p>
                <p>Specie: {selectedCharacter.species}</p>
                <p>Location: {selectedCharacter.location.name}</p>
            </div>
        </div>
    );
}

export default CharacterDetail;