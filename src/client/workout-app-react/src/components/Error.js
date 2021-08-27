

function Error({ errorMessages }) {
    if (errorMessages !== undefined) {
        return (
            <div className="alert alert-danger col-5" role="alert">
                {
                    errorMessages.map((m) => {
                        return (<div>{m}</div>)
                    })
                }

            </div>
        );
    } else {
        return <div></div>;
    }
}

export default Error;