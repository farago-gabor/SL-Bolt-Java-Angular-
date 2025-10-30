import { NapIdopontDTO } from './nap-idopont-dto.model';

export interface TevekenysegDTO {
    id: number
    megnevezes: string;
    leiras: string;
    gyakorisag: string;     // e.g. "HETI", "MINDIG", etc.
    kezdoDatum: string;     // ISO string, e.g. "2025-11-01"
    idopontok: NapIdopontDTO[];
}
