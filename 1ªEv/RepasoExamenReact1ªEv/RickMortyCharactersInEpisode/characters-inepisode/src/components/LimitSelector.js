function LimitSelector({limit, onLimitChange}) {
    return(
        <div className="limit-selector">
            <label>Mostrar: </label>
            <select value={limit}
                    onChange={(e) => onLimitChange(Number(e.target.value))}
                    className="limit-select">
                <option value={999}>Todos</option>
                <option value={5}>5 resultados</option>
                <option value={10}>10 resultados</option>
                <option value={20}>20 resultados</option>
            </select>
        </div>
    );
}

export default LimitSelector;