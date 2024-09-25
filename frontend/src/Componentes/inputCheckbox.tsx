interface InputProps {
    label: string;
    type: string;
    value: string;
    setValue: Function;
    inputClassName: string;
    labelClassName: string;
}

export function Check(props: InputProps) {
    return (
        <div className="flex flex-row space-x-2">
            <div>
            <input type={props.type} onChange={e => props.setValue(e.target.value)}
                value={props.value} className={props.inputClassName} />
            </div>

            <div>
            <label className={props.labelClassName}>{props.label}</label>
            </div>
        </div>
    );
}
