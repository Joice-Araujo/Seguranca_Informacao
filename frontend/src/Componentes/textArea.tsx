interface TextAreaProps {
  label: string;
  labelClassname: string
  inputClassname: string
  setValue: Function
  rows : number
  cols : number
  value: string
}

export function TextArea({cols, rows ,inputClassname, labelClassname, label, setValue, value }: TextAreaProps) {
  return (
    <div className="flex flex-col">
      <label className={labelClassname}>{label}</label>
      <textarea  className={inputClassname} onChange={(e) => setValue(e.target.value)} value={value} rows={rows} cols={cols} />
    </div>
  );
}