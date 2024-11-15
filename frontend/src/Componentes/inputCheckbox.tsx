interface InputProps {
    label: string;
    type: string;
    value: boolean;
    setValue: Function;
    inputClassName: string;
    labelClassName: string;
}

export function Check(props: InputProps) {

    const handleCheckBox = () => {
        props.setValue()
    }

    return (
        <div className="flex flex-row space-x-2">
            <div>
                <input type={props.type} onChange={() => {
                    handleCheckBox()
                }}
                 className={props.inputClassName}
                 checked={props.value}
                 />
            </div>

            <div>
                <label className={props.labelClassName}>{props.label}</label>
            </div>
        </div>
    );
}
