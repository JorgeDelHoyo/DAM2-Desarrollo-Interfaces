import './CharacterDetail.css';

function CharacterDetail({character, onClose}) {
    return(
        <div className='modal-overlay' onClick={onClose}>
            <div className="modal-content">
                <button onClick={onClose}>X</button>
                <img src={character.image} alt={character.name} />
                
                <h2>{character.name}</h2>
                
                <div className="info-list">
                    <p><strong>Status:</strong> {character.status}</p>
                    <p><strong>Gender:</strong> {character.gender}</p>
                    <p><strong>Species:</strong> {character.species}</p>
                    <p><strong>Location:</strong> {character.location.name}</p>
                </div>
            </div>
        </div>
    );
}

export default CharacterDetail;