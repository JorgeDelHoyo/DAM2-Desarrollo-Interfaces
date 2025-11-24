import './EpisodeList.css';

function EpisodeList({episodes, onClick}) {
    return(
        <div className="episode-list">
            {episodes.map((episode) => (
                <h3
                    className="episode-card"
                    key={episode.id}
                    onClick={() => onClick(episode)}
                >{episode.name}</h3>
            ))}
        </div>
    );
}

export default EpisodeList;