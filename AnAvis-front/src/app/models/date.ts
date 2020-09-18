import {Prenotation} from './prenotation';
export class Date {
	public id: number;
	public place: string
	public active: boolean;
	public description: string;
	public prenotationDate: string;
	public remainingNumber: number;
	public prenotationList: Prenotation[];
}
