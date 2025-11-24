import './CharacterDetail.css';

function CharacterDetail({characterSelected, onClose}) {
    return(
        <div onClick={onClose} className="modal-overlay">
            <div className="modal-content">
                <button onClick={onClose}>X</button>
                <img src={characterSelected.image} />
                <h3>Name: {characterSelected.name}</h3>
                <p>Status: {characterSelected.status}</p>
                <p>Gender: {characterSelected.gender}</p>
                <p>Specie: {characterSelected.species}</p>
                <p>Location: {characterSelected.location.name}</p>
            </div>
        </div>
    );
}

export default CharacterDetail;