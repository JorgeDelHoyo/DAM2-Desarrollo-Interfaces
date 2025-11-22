import './CharacterDesplegado.css';
function CharacterDesplegado({selectedCharacter, closeDetails}){
    return(
        <div onClick={closeDetails} className="modal-overlay">
            <div className="modal-content">
                <button onClick={closeDetails} className="modal-close-button">X</button>
                <img src={selectedCharacter.image} />
                <div className="modal-info">
                    <h2>{selectedCharacter.name}</h2>
                    <p>
                        <span className="info-label">Gender:</span>
                        {selectedCharacter.gender}
                    </p>
                </div>
            </div>
        </div>
    );
}

export default CharacterDesplegado;