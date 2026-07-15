import "../../styles/cards.css";

function StatCard({
    title,
    value,
    icon,
    bgColor
}) {

    return (

        <div className="col-lg-3 col-md-6 mb-4">

            <div className="card stat-card h-100">

                <div className="card-body">

                    <div className="d-flex justify-content-between align-items-center">

                        <div>

                            <h6 className="text-muted">
                                {title}
                            </h6>

                            <h2 className="fw-bold mt-2">
                                {value}
                            </h2>

                        </div>

                        <div
                            className="stat-icon"
                            style={{ backgroundColor: bgColor }}
                        >
                            <i className={icon}></i>
                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StatCard;