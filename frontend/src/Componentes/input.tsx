interface InputProps {
  label: string;
  type: string;
  value: string;
  setValue: Function;
  inputClassName: string;
  labelClassName: string;
  disabled?: boolean;
}

export function Input(props: InputProps) {
  return (
    <div className="flex flex-col">
      <label className={props.labelClassName}>{props.label}</label>
      <input type={props.type}  onChange={e => props.setValue(e.target.value)}
                    value={props.value} className={props.inputClassName} disabled={props.disabled} />
    </div>
  );
}
