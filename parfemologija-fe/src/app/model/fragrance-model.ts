export class FragranceModel {
  constructor(
    public id: number,
    public slug: string,
    public officialName: string,
    public brandId: number,
    public typeId: number,
    public winterRating: number,
    public fallRating: number,
    public summerRating: number,
    public springRating: number,
    public sillageRating: number,
    public longevityRating: number,
    public priceRange: string,
    public createdBy: string,
    public createdAt: Date,
    public modifiedBy: string,
    public modifiedAt: Date,
    public gender: string,
    public thumbnailImageUrl: string,
  ) { }
}

export class FragranceCreatModel {
  constructor(
    public officialName: string,
    public brandId: number,
    public typeId: number,
    public winterRating: number,
    public fallRating: number,
    public summerRating: number,
    public springRating: number,
    public sillageRating: number,
    public longevityRating: number,
    public priceRange: string,
    public gender: string
  ) { }
}