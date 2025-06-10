export interface FragranceReview {
  id: number;
  head: string;
  body: string;
  rating: number;
  createdBy: string;
  createdAt: string;
  fragranceId: number;
}

export class FragranceReviewCreate{
    constructor(
        public head: string,
        public body: string,
        public rating: number,
        public fragranceId: number
    ){}
}
