export interface RendelesDTO {
  id: number;
  email: string;
  telefonszam: string;
  hatarido: string; // ISO formátumban: "2025-10-02"
  dolgozoNev: string;
  status: RendelesStatus;
  //beerkezet: boolean;
  //felreteve: boolean;
  //szoltam: boolean;
  //elvitte: boolean;
}

export enum RendelesStatus {
  NINCS = 'NINCS',
  BEERKEZETT = 'BEERKEZETT',
  FELRETEVE = 'FELRETEVE',
  SZOLTAM = 'SZOLTAM',
  ELVITTE = 'ELVITTE'
}