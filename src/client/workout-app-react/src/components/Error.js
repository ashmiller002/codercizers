import './Error.css';

function Error({ errorMessages }) {
    if (errorMessages !== undefined) {
        return (
            <div className="materialert danger">
                {
                    errorMessages.map((m) => {
                        return (<div key={errorMessages.indexOf(m)}>{m}</div>)
                    })
                }

            </div>
        );
    } else {
        return <div></div>;
    }
}

export default Error;