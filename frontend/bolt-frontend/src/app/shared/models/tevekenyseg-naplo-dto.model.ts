import { TevekenysegDTO } from './tevekenyseg-dto.model';
import { Dolgozo } from './dolgozo.model';

export interface TevekenysegNaploDTO {
    id: number;
    tevekenyseg: TevekenysegDTO;
    dolgozoId: Dolgozo;
    datum: string; // ISO string
}