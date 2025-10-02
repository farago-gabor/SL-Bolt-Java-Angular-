export interface RendelesDTO {
  id: number;
  email: string;
  telefonszam: string;
  hatarido: string; // ISO formátumban: "2025-10-02"
  dolgozoNev: string;
  beerkezet: boolean;
  felreteve: boolean;
  szoltam: boolean;
  elvitte: boolean;
}
