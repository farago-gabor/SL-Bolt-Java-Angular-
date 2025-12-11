import { TevekenysegDTO } from './tevekenyseg-dto.model';
import { Dolgozo } from './dolgozo.model';

export interface NaploDTO {
    id: number;
    dolgozoNev: string;
    tevekenysegMegnevezes: string;
    tevekenysegLeiras: string;
    datum: string; // ISO string
    nap?: string;    // pl.: "hetfo"
    idopont?: string; // pl.: "08:00"
}