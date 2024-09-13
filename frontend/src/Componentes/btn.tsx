
interface BtndProps {
  label: string;
  onClick: () => void;
  btnClassName : string;
}

export function Btn(props: BtndProps) {
  return (
    <button
      onClick={props.onClick}
      className={props.btnClassName}
    >
      {props.label}
    </button>
  );
}
